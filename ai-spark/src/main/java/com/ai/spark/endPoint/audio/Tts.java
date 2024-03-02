package com.ai.spark.endPoint.audio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tts {

    /**
     * 发⾳⼈，必传
     */
    private String vcn;

    /**
     * 语速：0对应默认语速的1/2，100 对应默认语速的2倍
     * 最⼩值:0, 最⼤值:100
     */
    private Integer speed;

    /**
     * ⾳量：0是 静⾳，1对 应默认⾳量 1/2，100对应默认⾳量的2倍
     * 最⼩值:0, 最⼤值:100
     */
    private Integer volume;

    /**
     * 语调：0对应默认语速的1/2，100对应默认语速的2倍
     * 最⼩值:0, 最⼤值:100
     */
    private Integer pitch;

    /**
     * 背景⾳
     * 0:⽆背景⾳, 1:内置背景⾳1, 2:内置背景⾳2
     */
    private Integer bgs;

    /**
     * 英⽂发⾳⽅式
     * 0:⾃动判断处理，如果不确定将按照英⽂词语拼写处理（缺省）,
     * 1:所有英⽂按字⺟发⾳,
     * 2:⾃动判断处理，如果不确定将按照字⺟朗读
     */
    private Integer reg;

    /**
     * 合成⾳频数字发⾳⽅式
     * 0:⾃动判断, 1:完全数值, 2:完全字符串, 3:字符串优先
     */
    private Integer rdn;

    /**
     * 是否返回拼⾳标注
     * 0:不返回拼⾳, 1:返回拼⾳（纯⽂本格式，utf8编码）
     */
    private Integer rhy;

    /**
     * 场景
     * 0:⽆, 1:散⽂阅读, 2:⼩说阅读, 3:新闻, 4:⼴告, 5:交互
     */
    private Integer scn;

    /**
     * 引擎初始化，是否返回版本信息+时间戳信息
     * 0:不返回, 1:返回版本信息+时间戳信息。如XXX.18928127 XXX表示版本号，后接秒为单位的时间戳
     */
    private Integer version;

    /**
     * 控制L5静⾳时⻓，取值范围为 0~10000ms
     * 最⼩值:0, 最⼤值:10000
     */
    private Integer L5SilLen;

    /**
     * 段落静⾳时⻓，取值范围为0~10000ms
     * 最⼩值:0, 最⼤值:10000
     */
    private Integer ParagraphSilLen;

    private Audio audio;

    private Pybuf pybuf;

    @Getter
    @AllArgsConstructor
    public enum Vcn {
        lxx("x4_lingxiaoxuan_oral"),
        lfz("x4_lingfeizhe_oral"),
        lyz("x4_lingyuzhao_oral");
        private String name;
    }

}
