package com.zom.factory.zfactory.generator.template.db.generator;

import com.zom.factory.zfactory.generator.template.db.core.CoreGenerator;

public class MybatisGenerator {

    public static void main(String[] args) throws Exception {
        //String [] tables = {"rtv_fence","rtv_plan_action","rtv_plan_event","rtv_plan_rule","rtv_plan_rule_action_map","rtv_plan_rule_user_map"};
        //String [] tables = {"rtv_location","rtv_route","rtv_route_loc","rtv_patrol","rtv_patrol_user","rtv_patrol_user_loc"};
        //String[] tables = {"user"};
        //String [] tables = {"rtv_nfc","rtv_nfc_record"};
        //String[] tables = {"rtv_role_plan", "rtv_role_plan_role"};
        String[] tables = {"rtv_task_charge", "rtv_task_exec"};
        CoreGenerator.produceAll(1, tables, true, true, true, false);
    }

}
