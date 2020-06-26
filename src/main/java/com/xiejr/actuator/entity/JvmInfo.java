package com.xiejr.actuator.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @program: socket-actuator-spring-boot-starter
 * @description: java虚拟机信息
 * @author: xjr
 * @create: 2020-06-17 11:02
 **/

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JvmInfo extends BaseEntity {

    /**
     * 操作系统名称
     */
    private String osName;

    /**
     *堆内存大小
     */
    private Long heapMemory;

    /**
     *栈内存大小
     */
    private Long stackMemory;


    /**
     *cpu频率
     */
    private Long cpuFrequencyInHz;

    /**
     *cpu数
     */
    private Integer cpuNums;

    /**
     *cpu使用度
     */
    private Float cpuUseAge;


    /**
     *内存剩余字节数
     */
    private Long memoryFreeBytes;


    /**
     *总内存字节数
     */
    private Long memoryTotalBytes;


    /**
     * 内存使用情况
     * @return Float
     */
    public Float getMemoryUsage() {
        return (float) (1 - (memoryFreeBytes * 1.0 / memoryTotalBytes));
    }

}
