package com.iweb.IOStream;

import java.io.File;

public class Demo08 {
    public static void main(String[] args) {

        File file = new File("D:\\");
      /*  //返回目录中的子文件数组
        File[] files = file.listFiles();
        files[0].listFiles();*/
        find_MP4_Files(file);
    }

    /*
     * 此方法用于递归查找磁盘中所有MP4文件*/
    static void find_MP4_Files(File targetFolder) {
        if (targetFolder.isFile()) {
//            获取文件名
            String name=targetFolder.getName();
//            获取文件后缀名
            if(name.endsWith(".docx")){
                //打印文件的绝对路径
                System.out.println(targetFolder.getPath());
            }
            return;//如果targetFolder是文件，停止递归
        }
        if (targetFolder.isDirectory()) {
            //拿到该目录的子文件
            File[] files = targetFolder.listFiles();
            if(files==null||files.length==0){
                //如果这是一个空目录，停止递归，防止空指针异常
                return;
            }
            //遍历子文件数组
            for (int i=0;i< files.length;i++){
                //递归
                find_MP4_Files(files[i]);
            }
        }
    }
}
