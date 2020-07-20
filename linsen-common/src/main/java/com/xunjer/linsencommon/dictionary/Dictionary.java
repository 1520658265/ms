package com.xunjer.linsencommon.dictionary;

/**
 * @author yuansheng
 * @Title: Dictionary
 * @Description: 通用字典
 * @date 2020/7/115:04
 */
public class Dictionary {

    public static enum ReturnCode{
        Success(0),
        Error(5000),
        Unknown(1000),
        UnAuth(6000),
        UnLogin(2000),
        TokenValid(3000),
        NotFound(4000);

        private int key;

        private ReturnCode(final int key){
            this.key = key;
        }

        public Integer getKey(){
            return this.key;
        }
    }

    public static enum TaskState{
        NotStart(0,"未开始"),
        Working(1,"进行中"),
        NotFinish(2,"未完成"),
        Finished(3,"完成");

        private int key;

        private String desc;

        private TaskState(final int key,String desc){
            this.key=key;
            this.desc=desc;
        }

        public Integer getKey(){
            return this.key;
        }
    }

    public static enum DeleteFlag{
        Delete(1),
        NotDelete(0);
        private int key;

        private DeleteFlag(final int key){
            this.key = key;
        }

        public int key(){
            return this.key;
        }
    }
}
