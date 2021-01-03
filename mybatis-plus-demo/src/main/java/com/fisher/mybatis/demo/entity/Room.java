package com.fisher.mybatis.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Room {


    /**
     * name : 直播房间名
     * roomid : 1
     * cover_img : http://mmbiz.qpic.cn/mmbiz_jpg/Rl1RuuhdstSfZa8EEljedAYcbtX3Ejpdl2et1tPAQ37bdicnxoVialDLCKKDcPBy8Iic0kCiaiaalXg3EbpNKoicrweQ/0?wx_fmt=jpeg
     * live_status : 101
     * start_time : 1568128900
     * end_time : 1568131200
     * anchor_name : 李四
     * anchor_img : http://mmbiz.qpic.cn/mmbiz_jpg/Rl1RuuhdstSfZa8EEljedAYcbtX3Ejpdlp0sf9YTorOzUbGF9Eib6ic54k9fX0xreAIt35HCeiakO04yCwymoKTjw/0?wx_fmt=jpeg
     * goods : [{"cover_img":"http://mmbiz.qpic.cn/mmbiz_png/FVribAGdErI2PmyST9ZM0JLbNM48I7TH2FlrwYOlnYqGaej8qKubG1EvK0QIkkwqvicrYTzVtjKmSZSeY5ianc3mw/0?wx_fmt=png","url":"pages/index/index.html","price":1100,"name":"fdgfgf"}]
     */

    private String name;
    private int roomid;
    private String cover_img;
    private int live_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_time;
    private Date end_time;
    private String anchor_name;
    private String anchor_img;
    private List<GoodsBean> goods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public int getLive_status() {
        return live_status;
    }

    public void setLive_status(int live_status) {
        this.live_status = live_status;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getAnchor_name() {
        return anchor_name;
    }

    public void setAnchor_name(String anchor_name) {
        this.anchor_name = anchor_name;
    }

    public String getAnchor_img() {
        return anchor_img;
    }

    public void setAnchor_img(String anchor_img) {
        this.anchor_img = anchor_img;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * cover_img : http://mmbiz.qpic.cn/mmbiz_png/FVribAGdErI2PmyST9ZM0JLbNM48I7TH2FlrwYOlnYqGaej8qKubG1EvK0QIkkwqvicrYTzVtjKmSZSeY5ianc3mw/0?wx_fmt=png
         * url : pages/index/index.html
         * price : 1100
         * name : fdgfgf
         */

        private String cover_img;
        private String url;
        private int price;
        private String name;

        public String getCover_img() {
            return cover_img;
        }

        public void setCover_img(String cover_img) {
            this.cover_img = cover_img;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", roomid=" + roomid +
                ", cover_img='" + cover_img + '\'' +
                ", live_status=" + live_status +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", anchor_name='" + anchor_name + '\'' +
                ", anchor_img='" + anchor_img + '\'' +
                ", goods=" + goods +
                '}';
    }
}
