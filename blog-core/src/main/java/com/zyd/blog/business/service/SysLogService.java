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
package com.zyd.blog.business.service;


import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.Log;
import com.zyd.blog.business.vo.LogConditionVO;
import com.zyd.blog.framework.object.AbstractService;

/**
 * @author yadong.zhang email:yadong.zhang0415(a)gmail.com
 * @version 1.0
 * @date 2018/01/09 17:40
 * @since 1.0
 */
public interface SysLogService extends AbstractService<Log, Integer> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    PageInfo<Log> findPageBreakByCondition(LogConditionVO vo);
}
