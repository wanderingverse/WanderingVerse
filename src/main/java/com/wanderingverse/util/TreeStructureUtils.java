package com.wanderingverse.util;

import com.wanderingverse.model.TreeNode;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.wanderingverse.config.CommonConfig.HASHMAP_INITIAL_CAPACITY;

/**
 * 通用树结构构造器
 *
 * @author WanderingVerse
 * @since 2025/08/17 02:36
 */
@Data
public class TreeStructureUtils {

    /**
     * 构建树结构
     *
     * @param nodeList 节点列表
     * @param <T>      节点内容类型
     * @return 树结构
     */
    public static <T> List<TreeNode<T>> buildTree(List<TreeNode<T>> nodeList) {
        if (ObjectUtils.isEmpty(nodeList)) {
            return Collections.emptyList();
        }
        Map<String, TreeNode<T>> nodeMap = nodeList.stream().collect(Collectors.toMap(TreeNode::getId, node -> node));
        List<TreeNode<T>> treeList = new ArrayList<>();
        for (TreeNode<T> node : nodeList) {
            String parentId = node.getParentId();
            if (!StringUtils.hasText(parentId) || "0".equals(parentId)) {
                treeList.add(node);
            } else {
                TreeNode<T> parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(node);
                } else {
                    treeList.add(node);
                }
            }
        }
        return treeList;
    }

    /**
     * 反构建树结构
     */
    public static <T> List<TreeNode<T>> reverseBuildTree(List<TreeNode<T>> treeList) {
        if (ObjectUtils.isEmpty(treeList)) {
            return Collections.emptyList();
        }
        List<TreeNode<T>> nodeList = new ArrayList<>();
        for (TreeNode<T> node : treeList) {
            if (ObjectUtils.isEmpty(node)) {
                continue;
            }
            Deque<TreeNode<T>> stack = new ArrayDeque<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                TreeNode<T> current = stack.pop();
                nodeList.add(current);
                List<TreeNode<T>> children = current.getChildren();
                if (!CollectionUtils.isEmpty(children)) {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        stack.push(children.get(i));
                    }
                }
            }
        }
        return nodeList;
    }

    /**
     * 以指定节点为根节点构建树
     *
     * @param nodeList 节点列表
     * @param rootId   根节点 id
     * @param <T>      节点内容类型
     * @return 根节点对应的树结构
     */
    public static <T> List<TreeNode<T>> buildTreeByRoot(List<TreeNode<T>> nodeList, String rootId) {
        if (ObjectUtils.isEmpty(nodeList) || !StringUtils.hasText(rootId)) {
            return Collections.emptyList();
        }
        Map<String, TreeNode<T>> nodeMap = nodeList.stream().collect(Collectors.toMap(TreeNode::getId, node -> node));
        Map<String, List<TreeNode<T>>> parentMap = new HashMap<>(HASHMAP_INITIAL_CAPACITY);
        for (TreeNode<T> node : nodeList) {
            parentMap.computeIfAbsent(node.getParentId(), k -> new ArrayList<>()).add(node);
        }
        List<TreeNode<T>> treeList = new ArrayList<>();
        TreeNode<T> rootNode = nodeMap.get(rootId);
        if (rootNode != null) {
            Deque<TreeNode<T>> stack = new ArrayDeque<>();
            stack.push(rootNode);
            while (!stack.isEmpty()) {
                TreeNode<T> current = stack.pop();
                List<TreeNode<T>> children = parentMap.get(current.getId());
                if (!CollectionUtils.isEmpty(children)) {
                    current.setChildren(children);
                    for (int i = children.size() - 1; i >= 0; i--) {
                        stack.push(children.get(i));
                    }
                }
            }
            treeList.add(rootNode);
        }
        return treeList;
    }
}
