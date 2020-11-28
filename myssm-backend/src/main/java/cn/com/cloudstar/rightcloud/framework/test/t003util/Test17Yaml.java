package cn.com.cloudstar.rightcloud.framework.test.t003util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hong.Wu
 * @date: 15:49 2020/02/12
 */
public class Test17Yaml {

    public static void main(String[] args) {
        Yaml yaml = new Yaml();
        //文件路径是相对类目录(src/main/java)的相对路径
        InputStream in = Test17Yaml.class.getClassLoader().getResourceAsStream("test.yaml");
        System.out.println(in);
        //File configFile = new File(TestYaml.class.getClassLoader().getResource("test.yaml").getFile());
        //System.out.println(configFile);

//        Map<String, Object> map = yaml.loadAs(in, Map.class);
//        String appid = map.getOrDefault("appid", "123").toString();
//        System.out.println(appid);
//        String port = ((Map<String, Object>) map.get("server")).get("port").toString();
//        System.out.println(port);

        String content = "{\"content\":\"---\\r\\n- hosts: all\\r\\n  #gather_facts: no\\r\\n  vars_files:\\r\\n    - /root/ansible_work/playbooks/vars/global.yml\\r\\n  vars:\\r\\n    MYSQL_HOST: 127.0.0.1\\r\\n    MYSQL_USER: root\\r\\n    MYSQL_PWD: 123456\\r\\n    MYSQL_DB: rightcloud\\r\\n    BACKUP_DIR: /usr/local/rightcloud/mysqlbackup/\\r\\n  pre_tasks:\\r\\n    - set_fact: USE_REPO_MIRROR=false\\r\\n      when: ansible_system != \\\"Linux\\\" or ansible_distribution == \\\"Amazon\\\" or ansible_distribution == \\\"SLES\\\"\\r\\n  environment:\\r\\n    PYTHONIOENCODING: 'utf-8'\\r\\n  tasks:\\r\\n\\r\\n    - name: \\\"Create required dir\\\"\\r\\n      file: path={{node_work_root_path}} state=directory\\r\\n\\r\\n    - name: \\\"Ignore error Check Repo mirror is reachable\\\"\\r\\n      uri:\\r\\n        url: \\\"http://{{ REPOMIRROR_URI }}\\\"\\r\\n        status_code: 200\\r\\n        timeout: 3\\r\\n      ignore_errors: yes\\r\\n      register: repo_status\\r\\n\\r\\n    - set_fact:\\r\\n        USE_REPO_MIRROR: \\\"{% if USE_REPO_MIRROR %}{{ (repo_status.status | int) == 200}}{% else %}False{% endif %}\\\"\\r\\n\\r\\n    - name: \\\"[show]Starting backup MySQL\\\"\\r\\n      shell: \\\"echo starting backup mysql\\\"\\r\\n\\r\\n    - name: \\\"[show]Check backup is exist\\\"\\r\\n      stat: path={{BACKUP_DIR}}\\r\\n      register: p\\r\\n\\r\\n    - name: \\\"Create backup dir\\\"\\r\\n      file: path={{BACKUP_DIR}} state=directory\\r\\n      when: not p.stat.exists\\r\\n\\r\\n    - name: \\\"[show]basic packages\\\"\\r\\n      yum: name={{ packages }} state=present\\r\\n      vars:\\r\\n        packages:\\r\\n        - libselinux-python\\r\\n        - python-deltarpm\\r\\n      when: not (USE_REPO_MIRROR | bool)\\r\\n\\r\\n    - name: \\\"[show]basic packages with rc mirror\\\"\\r\\n      yum:\\r\\n        name: \\\"{{ packages }}\\\"\\r\\n        state: present\\r\\n        disablerepo: \\\"*\\\"\\r\\n        enablerepo: \\\"11111rc-mirror\\\"\\r\\n      vars:\\r\\n        packages:\\r\\n        - libselinux-python\\r\\n        - python-deltarpm\\r\\n      when: (USE_REPO_MIRROR | bool)\\r\\n\\r\\n    - name: Copy mysqldump bin file\\r\\n      copy: src={{work_root_path}}/files/mysql/mysqldump dest=/usr/bin/mysqldump owner=root group=root mode=0655\\r\\n\\r\\n    - name: \\\"[show]backup mysql\\\"\\r\\n      shell: \\\"mysqldump -h{{MYSQL_HOST}} -u{{MYSQL_USER}} --password=\\\\\\\"{{MYSQL_PWD}}\\\\\\\" {{MYSQL_DB}} > {{BACKUP_DIR}}/{{MYSQL_DB}}_{{MYSQL_HOST}}_dump_$(date +%Y%m%d%H%M%S).sql\\\"\\r\\n\\r\\n    - name: \\\"[show]#Success#\\\"\\r\\n      shell: echo \\\"[show]#Success#\\\"\"}";
        // 读取yaml文件
        Object load = yaml.load(content);
        Map<String, Object> tempMap = (Map<String, Object>) load;

        Object yamlStr = tempMap.get("content");
        System.out.println(yamlStr);
        List<Map> list = (List) yaml.load(yamlStr.toString());
        System.out.println(list);
        for (Map map : list) {
            Map vars = (Map) map.get("vars");
            vars.forEach((k, v) -> {
                System.out.println(k + "  " + v);
            });
        }

        // 建立一个yaml文件
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);



        LinkedHashMap<String, Object> inputs = new LinkedHashMap<>(16);
        Map<String, Object> input = new HashMap<>(16);
        input.put("name", "计费方式");
        input.put("apply_visibility", true);
        input.put("approve_visibility", true);
        input.put("component", "select");
        input.put("search_flg", false);
        input.put("type", "list");
        input.put("support_envs", Arrays.asList("KingStack"));
        List<Object> envConstraints = new ArrayList<>(16);
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("cn-shanghai-2a", Arrays.asList(ImmutableMap.of("name", "按日月结", "value", "PostPaidByDaily", "checked", true)));
        envConstraints.add(map1);
        Map<Object, Object> constraints = Maps.newHashMap();
        constraints.put(1475, envConstraints);
        input.put("constraints", constraints);
        inputs.put("charge_type", input);

        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("description", "desc");
        resultMap.put("topology_template", ImmutableMap.of("inputs", inputs, "actions", "", "outputs", ""));
        String dump = new Yaml(options).dump(resultMap);


        System.out.println("dump=" + dump);


    }
}
