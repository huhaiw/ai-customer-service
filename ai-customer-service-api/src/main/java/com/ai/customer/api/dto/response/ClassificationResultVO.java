package com.ai.customer.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题分类结果
 */
@Data
@Builder
@Schema(description = "问题分类结果")
public class ClassificationResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "分类ID")
    private Long classificationId;

    @Schema(description = "分类编码")
    private String code;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类类型：ANSWERABLE-可回答，CLARIFY-需澄清，OUT_OF_SCOPE-超范围，DANGEROUS-危险敏感")
    private String type;

    @Schema(description = "置信度（0-100）")
    private Integer confidence;

    @Schema(description = "是否主要分类")
    private Boolean isPrimary;
}
