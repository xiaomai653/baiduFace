package com.jni.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
    public class GroupVo {
    /**
     * data : {"group_id_list":[{"group_id":"group1"}],"log_id":"1675410199"}
     * errno : 0
     * msg : success
     */

    private Data data;
    private int errno;
    private String msg;

    @NoArgsConstructor
    @lombok.Data
    public static class Data {
        /**
         * group_id_list : [{"group_id":"group1"}]
         * log_id : 1675410199
         */

        private String log_id;
        private List<GroupIdList> group_id_list;

        @NoArgsConstructor
        @lombok.Data
        public static class GroupIdList {
            /**
             * group_id : group1
             */

            private String group_id;
        }
    }
}
