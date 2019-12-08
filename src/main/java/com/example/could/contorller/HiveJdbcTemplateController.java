package com.example.could.contorller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.List;

/**
 * 使用 JdbcTemplate 操作 Hive
 */
@Slf4j
@Controller
@RequestMapping("/hive2")
public class HiveJdbcTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(HiveJdbcTemplateController.class);

    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate hiveJdbcTemplate;

    /**
     * 示例：创建新表
     */
    @ResponseBody
    @GetMapping("/table/create")
    public String createTable(Model model) {
        StringBuffer sql = new StringBuffer("CREATE TABLE IF NOT EXISTS ");
        sql.append("user_sample");
        sql.append("(user_num BIGINT, user_name STRING, user_gender STRING, user_age INT)");
        sql.append("ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' "); // 定义分隔符
        sql.append("STORED AS TEXTFILE"); // 作为文本存储

        logger.info("Running: " + sql);
        String result = "Create table successfully...";
        try {
            // hiveJdbcTemplate.execute(sql.toString());
            hiveDruidTemplate.execute(sql.toString());

        } catch (DataAccessException dae) {
            result = "Create table encounter an error: " + dae.getMessage();
            logger.error(result);
        }
        model.addAttribute("result", result);
        return result;

    }

    @ResponseBody
    @GetMapping("test")
    public int test(){
        String sql = "select count(*) from test";
        log.info(sql);
        Integer count = hiveDruidTemplate.queryForObject(sql, Integer.class);
        assert count != null;
        log.info(count.toString());
        return count;
    }
}
