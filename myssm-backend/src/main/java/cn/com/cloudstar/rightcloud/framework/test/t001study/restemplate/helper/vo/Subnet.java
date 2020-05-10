/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package cn.com.cloudstar.rightcloud.framework.test.t001study.restemplate.helper.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author ActiveEon Team
 * @since 10/01/17
 */
@Getter(AccessLevel.PUBLIC)
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subnet {

    @JsonProperty("active_discovery")
    private Boolean activeDiscovery;

    @JsonProperty("space")
    private String space;

    @JsonProperty("rdns_mode")
    private Long rdnsMode;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("gateway_ip")
    private String gatewayIp;

    @JsonProperty("vlan")
    private Vlan vlan;

    @JsonProperty("allow_proxy")
    private Boolean allowProxy;

    @JsonProperty("resource_uri")
    private String resourceUri;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cidr")
    private String cidr;

    @JsonProperty("dns_servers")
    private String[] dnsServers;
}
