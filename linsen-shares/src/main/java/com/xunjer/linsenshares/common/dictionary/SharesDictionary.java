package com.xunjer.linsenshares.common.dictionary;

/**
 * @author yuansheng
 * @Title:
 * @Description:
 * @date 2020/8/1415:23
 */
public class SharesDictionary {

    public static enum Sum_Up {
        Up(1), Down(-1);
        private int key;

        private Sum_Up(final int key) {
            this.key = key;
        }

        public int key() {
            return this.key;
        }
    }
}
