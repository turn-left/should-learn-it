## kubernetes 实践

### 安装过程 & 问题解决

#### 基于virtualbox安装ubuntu 18.0.4

#### [官网安装 kubeadm](https://kubernetes.io/zh/docs/setup/production-environment/tools/kubeadm/install-kubeadm/#installing-runtime)

- 下载 Google Cloud 公开签名秘钥问题  
  使用阿里云:  
  sudo curl -fsSLo
  /usr/share/keyrings/kubernetes-archive-keyring.gpg https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg
- 无法下载kubeadm kubelet kubectl  
  解决办法:  
  echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] http://mirrors.ustc.edu.cn/kubernetes/apt kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list

#### [官网使用 kubeadm 创建集群](https://kubernetes.io/zh/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/)

#### 参考博文
- [无法下载kubeadm kubelet kubectl](https://blog.csdn.net/qq_39698985/article/details/114983293)
- [k8s安装过程爬坑](https://blog.csdn.net/BiaoYBbiao/article/details/99586993?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control&dist_request_id=1331979.15005.16186720169130729&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-3.control)