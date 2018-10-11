/**
 * Copyright [2016-2018] [yadong.zhang]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:36
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLog extends AbstractDO {
	private Long userId;
	private String logLevel;
	private String ip;
	private String content;
	private String type;
	private String ua;
	private String os;
	private String browser;
	private String spiderType;
	private String requestUrl;
	private String referer;
}
