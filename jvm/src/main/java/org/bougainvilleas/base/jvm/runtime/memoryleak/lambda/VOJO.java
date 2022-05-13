package org.bougainvilleas.base.jvm.runtime.memoryleak.lambda;

import lombok.Builder;
import lombok.Data;

/**
 * @author renqiankun
 * @date 2021-07-19 15:13:57 星期一
 */
@Data
@Builder
public class VOJO
{
    private String name;
    private AgeEnum type;
}
