package com.plumdo.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * hadoop jar /data/hadoop-demo-1.0.0.jar com.plumdo.hadoop.mr.WordCount /user/root/input/container-executor.cfg /user/root/output
 * <p>
 * 统计demo
 *
 * @author wengwh
 * @date 2020/10/20
 */
public class WordCount {


    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        //在hadoop集群机器上运行会读取相应的配置文件 $Hadoop_Home
        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCount.class);//上传Jar


        //map & reduce 过程设置
        job.setMapperClass(WordCountMap.class);//mapper 过程
        job.setReducerClass(WordCountReduce.class);//reduce 过程

        job.setMapOutputKeyClass(Text.class);//mapper 输出key
        job.setMapOutputValueClass(IntWritable.class);//mapper 输出value

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        //文件路径设置
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        boolean flag = job.waitForCompletion(true);

        System.out.println("the job exe is :" + flag);
    }

}

class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();

        String[] words = line.split(" ");

        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }

    }

}

class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text text, Iterable<IntWritable> iter,
                          Context context) throws IOException, InterruptedException {
        //初始化一个计数器用于叠加总次数
        int count = 0;

        for (IntWritable num : iter) {
            count = count + num.get();
        }

        //汇总结束,写出
        context.write(text, new IntWritable(count));
    }


}
