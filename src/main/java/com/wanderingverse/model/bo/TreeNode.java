package com.wanderingverse.model.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 *
 * @author WanderingVerse
 * @since 2025/08/17 02:43
 */
@Data
public class TreeNode<T> {

    /**
     * 节点 id
     */
    private String id;

    /**
     * 节点携带内容体
     */
    private T content;


    /**
     * 父节点 id
     * <p>空 或 0表示根节点
     */
    private String parentId;

    /**
     * 子节点列表
     */
    private List<TreeNode<T>> children = new ArrayList<>();
}
