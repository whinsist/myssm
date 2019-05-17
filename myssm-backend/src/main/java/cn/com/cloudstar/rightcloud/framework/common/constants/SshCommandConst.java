/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * \n
 * The type SshCommandConst.\n
 * \n
 * Created on 2016/9/29 \n
 *
 * @author Chaohong.Mao
 */
public interface SshCommandConst {
    String OS_BASE_INFO_CMD =
            "#/bin/bash\n" + "command_exists() {\n" + "   command -v \"$@\" > /dev/null 2>&1\n" + "}\n" +
                    "lsb_dist=''\n" + "dist_version=''\n" + "if command_exists lsb_release; then\n" +
                    "    lsb_dist=\"$(lsb_release -si)\"\n" + "fi\n" +
                    "if [ -z \"$lsb_dist\" ] && [ -r /etc/lsb-release ]; then\n" +
                    "    lsb_dist=\"$(. /etc/lsb-release && echo \"$DISTRIB_ID\")\"\n" + "fi\n" +
                    "if [ -z \"$lsb_dist\" ]; then\n" +
                    "    if [ -r /etc/centos-release ] || [ -r /etc/redhat-release ]; then\n" +
                    "        lsb_dist='centos'\n" + "    fi\n" + "fi\n" +
                    "if [ -z \"$lsb_dist\" ] && [ -r /etc/os-release ]; then\n" +
                    "    lsb_dist=\"$(. /etc/os-release && echo \"$ID\")\"\n" + "fi\n" +
                    "lsb_dist=\"$(echo \"$lsb_dist\" | tr '[:upper:]' '[:lower:]')\"\n" + "case \"$lsb_dist\" in\n" +
                    "    ubuntu)\n" + "        if command_exists lsb_release; then\n" +
                    "            dist_version=\"$(lsb_release --release | cut -f2)\"\n" + "        fi\n" +
                    "        if [ -z \"$dist_version\" ] && [ -r /etc/lsb-release ]; then\n" +
                    "            dist_version=\"$(. /etc/lsb-release && echo \"$DISTRIB_RELEASE\")\"\n" +
                    "        fi\n" + "    ;;\n" + "    centos)\n" +
                    "        dist_version=\"$(rpm -q --whatprovides redhat-release --queryformat \"%{VERSION}.%{RELEASE}\\n\" | cut -f1,2 -d.)\"\n" +
                    "    ;;\n" + "esac\n" +
                    "dist_version=\"$(echo \"$dist_version\" | tr '[:upper:]' '[:lower:]')\"\n" +
                    "unit_version=\"$(getconf LONG_BIT)\"\n" +
                    "echo -e \"[lsb]:${lsb_dist}#[dist]:${dist_version}#[unit]:${unit_version}\"";

    String OS_AIX_BASE_INFO_CMD =
            "#/bin/bash \n" + "command_exists() {\n" + "   command -v \"$@\" > /dev/null 2>&1\n" + "}\n" +
                    "lsb_dist=\"$(uname -s)\"\n" + "#dist_version=\"$(oslevel -s)\"\n" +
                    "dist_version=\"$(uname -v)\"\n" + "unit_version=''\n" + "if [ \"$lsb_dist\" == \"AIX\" ]; then\n" +
                    "    unit_version=\"$(bootinfo -K)\"\n" +
                    "    echo \"[lsb]:${lsb_dist}#[dist]:${dist_version}#[unit]:${unit_version}\"\n" + "else\n" +
                    "   echo \"hello\"\n" + "fi";

    String OS_AIX_TEST_CMD =
            "#/bin/bash \n" + "command_exists() {\n" + "   command -v \"$@\" > /dev/null 2>&1\n" + "}\n" +
                    "lsb_dist=\"$(uname -s)\"\n" + "#dist_version=\"$(oslevel -s)\"\n" +
                    "dist_version=\"$(uname -v)\"\n" + "unit_version=''\n" + "   echo \"hello\"\n";
}
