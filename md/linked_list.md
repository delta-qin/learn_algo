# 链表



## 前序后序遍历

```java
void traverse(Node node) {
  if(node == null) return;
  // 前序node.value
  traverse(node.next);
  // 后序node.value
}
```

```java
void traverse(Node node) {
  while(node != null) {
    // node.value
    node = node.next;
  }
}
```

