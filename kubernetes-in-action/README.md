## kubernetes 实践

### 安装过程 & 问题解决

#### 基于virtualbox安装ubuntu 18.0.4

#### [官方基于kubeadm安装集群](https://kubernetes.io/zh/docs/setup/production-environment/tools/kubeadm/install-kubeadm/#installing-runtime)

- 下载 Google Cloud 公开签名秘钥问题  
  使用阿里云:  
  sudo curl -fsSLo
  /usr/share/keyrings/kubernetes-archive-keyring.gpg https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg
- 无法下载kubeadm kubelet kubectl  
  解决办法:  
  echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] http://mirrors.ustc.edu.cn/kubernetes/apt
  kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
