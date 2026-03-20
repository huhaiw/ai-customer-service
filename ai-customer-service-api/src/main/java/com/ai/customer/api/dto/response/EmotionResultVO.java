package com.ai.customer.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 情绪分析结果
 */
@Data
@Builder
@Schema(description = "情绪分析结果")
public class EmotionResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "情绪类型：POSITIVE-积极，NEGATIVE-消极，NEUTRAL-中性，ANGRY-愤怒，ANXIOUS-焦虑")
    private String emotionType;

    @Schema(description = "情绪得分（0-100）")
    private Integer emotionScore;

    @Schema(description = "情绪等级（1-4）")
    private Integer emotionLevel;

    @Schema(description = "情绪强度（0-100）")
    private Integer intensity;

    @Schema(description = "是否需要人工介入")
    private Boolean needAgent;

    @Schema(description = "分析说明")
    private String description;
}
