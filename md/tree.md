# 树

## 树结构

## 遍历套路牢记

```java
void traverse(Node root) {
  // 前序操作
  traverse(root.left);
  // 中序操作
  traverse(root.right);
  // 后序操作
}
```

```java
void traverse(Node root) {
  for(Node ch : root.children)
    traverse(ch);
}
```



## 后序遍历 树型DP常用套路

先向左子树要信息，然后向右子树要信息，汇总之后返回相应结果



## 前缀树

