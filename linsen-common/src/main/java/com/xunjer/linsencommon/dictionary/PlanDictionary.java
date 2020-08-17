package com.xunjer.linsencommon.dictionary;

/**
 * @author yuansheng
 * @Title: 计划字典
 * @Description:
 * @date 2020/8/1711:06
 */
public class PlanDictionary {

    /**
     * 计划分类 1-年 2-月 3-周 4-日
     */
    public static enum PlanType {
        Year(1), Month(2), Week(3), Day(4);
        private int key;

        private PlanType(final int key) {
            this.key = key;
        }

        public int key() {
            return this.key;
        }
    }

    /**
     * 计划完成状态 0-未开始 1-进行中 2-未完成 4-完成
     */
    public static enum PlanState {
        NotStart(0), Running(1), NotFinish(2), Finish(3);
        private int key;

        private PlanState(final int key) {
            this.key = key;
        }

        public int key() {
            return this.key;
        }
    }
}
