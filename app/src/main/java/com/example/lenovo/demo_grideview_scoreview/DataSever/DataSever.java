package com.example.lenovo.demo_grideview_scoreview.DataSever;

import com.example.lenovo.demo_grideview_scoreview.been.MultipleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/26.
 */
public class DataSever {

    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();

//        ArrayList<String> iconList = new ArrayList<>();
//        iconList.add("http://192.168.0.14/qwer.jpg");
//        MultipleItem item=new MultipleItem(MultipleItem.oneBigImage,iconList);
//        list.add(item);


        for (int i = 0; i < 3; i++) {
            MultipleItem stu = new MultipleItem(MultipleItem.text, "小学生" + i);
            stu.setItemType(MultipleItem.text);
            list.add(stu);
        }

        MultipleItem imageItem2 = new MultipleItem();
        imageItem2.setItemType(MultipleItem.twnBigImage);
        ArrayList<String> iconList2 = new ArrayList<>();
        iconList2.add("http://192.168.0.14/qwer4.jpg");
        iconList2.add("http://192.168.0.14/qwer5.jpg");
        imageItem2.setList(iconList2);
        list.add(imageItem2);


        //三张图片的
        for (int i = 0; i < 3; i++) {
            MultipleItem imageItem3 = new MultipleItem();
            imageItem3.setItemType(MultipleItem.threeBigImage);
            ArrayList<String> iconList3 = new ArrayList<>();
            iconList3.add("http://192.168.0.14/qwer1.jpg");
            iconList3.add("http://192.168.0.14/qwer2.jpg");
            iconList3.add("http://192.168.0.14/qwer3.jpg");

            list.add(imageItem3);
        }

        for (int i = 0; i < 3; i++) {
            MultipleItem stu = new MultipleItem(MultipleItem.text, "小学生" + i);
            stu.setItemType(MultipleItem.text);
            list.add(stu);
        }

        MultipleItem imageItem4 = new MultipleItem();
        imageItem4.setItemType(MultipleItem.twnBigImage);
        ArrayList<String> iconList3 = new ArrayList<>();
        iconList3.add("http://192.168.0.14/qwer6.jpg");
        iconList3.add("http://192.168.0.14/qwer7.jpg");
        imageItem4.setList(iconList2);
        list.add(imageItem4);
        return list;
    }


    public static List<MultipleItem> getMultipleMoreData() {
        List<MultipleItem> list = new ArrayList<>();

//        ArrayList<String> iconList = new ArrayList<>();
//        iconList.add("http://192.168.0.14/qwer.jpg");
//        MultipleItem item=new MultipleItem(MultipleItem.oneBigImage,iconList);
//        list.add(item);


        for (int i = 0; i < 3; i++) {
            MultipleItem stu = new MultipleItem(MultipleItem.text, "小学生" + i);
            stu.setItemType(MultipleItem.text);
            list.add(stu);
        }

        MultipleItem imageItem2 = new MultipleItem();
        imageItem2.setItemType(MultipleItem.twnBigImage);
        ArrayList<String> iconList2 = new ArrayList<>();
        iconList2.add("http://192.168.0.14/qwer4.jpg");
        iconList2.add("http://192.168.0.14/qwer5.jpg");
        imageItem2.setList(iconList2);
        list.add(imageItem2);


        //三张图片的
        for (int i = 0; i < 3; i++) {
            MultipleItem imageItem3 = new MultipleItem();
            imageItem3.setItemType(MultipleItem.threeBigImage);
            ArrayList<String> iconList3 = new ArrayList<>();
            iconList3.add("http://192.168.0.14/qwer1.jpg");
            iconList3.add("http://192.168.0.14/qwer2.jpg");
            iconList3.add("http://192.168.0.14/qwer3.jpg");

            list.add(imageItem3);
        }

        for (int i = 0; i < 3; i++) {
            MultipleItem stu = new MultipleItem(MultipleItem.text, "小学生" + i);
            stu.setItemType(MultipleItem.text);
            list.add(stu);
        }

        MultipleItem imageItem4 = new MultipleItem();
        imageItem4.setItemType(MultipleItem.twnBigImage);
        ArrayList<String> iconList3 = new ArrayList<>();
        iconList3.add("http://192.168.0.14/qwer6.jpg");
        iconList3.add("http://192.168.0.14/qwer7.jpg");
        imageItem4.setList(iconList2);
        list.add(imageItem4);
        return list;
    }

}
