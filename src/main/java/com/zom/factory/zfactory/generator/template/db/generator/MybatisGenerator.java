package com.zom.factory.zfactory.generator.template.db.generator;

import com.zom.factory.zfactory.generator.template.db.core.CoreGenerator;

public class MybatisGenerator {

    public static void main(String[] args) throws Exception {
        //String [] tables = {"rtv_fence","rtv_plan_action","rtv_plan_event","rtv_plan_rule","rtv_plan_rule_action_map","rtv_plan_rule_user_map"};
        //String [] tables = {"rtv_location","rtv_route","rtv_route_loc","rtv_patrol","rtv_patrol_user","rtv_patrol_user_loc"};
        //String[] tables = {"user"};
        //String [] tables = {"rtv_nfc","rtv_nfc_record"};
        //String[] tables = {"rtv_role_plan", "rtv_role_plan_role"};
        //String[] tables = {"rtv_rollcall_result", "rtv_rollcall_statistics", "rtv_rollcall_task"};
        //String[] tables = {"rtv_feedback", "rtv_gps_info", "rtv_group_picture_map", "offline_video_record_table", "rtv_password_reset_code", "rtv_3rdkey", "rtv_workorder"};
        //String[] tables = {"rtv_user_group_map"};
        //String[] tables = {"rtv_app_black_list", "rtv_app_white_list", "rtv_illegal_app_record", "rtv_user_phone_record", "rtv_user_sms_record"};
        //String[] tables = {"tech_article", "tech_category", "tech_group", "tech_im", "tech_login_log", "tech_user"};
        //String[] tables = {"third_icc_t_alarm", "third_icc_t_alarm_feedback", "third_icc_t_alarm_process"};
       /* String[] tables = {"sta_day_dept", "sta_day_user", "sta_original_log", "sta_parse_position", "sta_user_audio_record",
                "sta_user_audio_record_origin", "sta_user_im_record", "sta_user_logon_record_origin", "sta_user_mileage_record",
                "sta_user_online_record", "sta_user_photo_record", "sta_user_sos_record", "sta_user_sos_record_origin",
                "sta_user_tmpgroup_record","sta_user_video_record","sta_user_video_record_origin"};*/
        //String[] tables = {"rtv_version_dept_forbid_map"};
        //String[] tables = {"rtv_simple_dict"};
        //String[] tables = {"rtv_metro_bind"};
        //String[] tables = {"rtv_feature_cfg","rtv_iccid_change_record"};
        String[] tables = {"rtv_rhtx","rtv_rhtx_channel"};
        CoreGenerator.produceAll(1, tables, true, true, true, false);
    }

}
