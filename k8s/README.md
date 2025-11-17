# ☸️ Kubernetes Deployment Guide

## 📋 Archivos disponibles

- **namespace.yaml**: Define el namespace magnix-app
- **mysql.yaml**: Deployment y Service de MySQL
- **magnix-app.yaml**: Deployment, Service y HPA de la aplicación

## 🚀 Cómo deployar

### Prerequisitos

- Kubernetes cluster (minikube, Docker Desktop K8s, GKE, EKS, AKS)
- kubectl instalado y configurado
- La imagen Docker debe estar en GitHub Container Registry

### Pasos

\\\ash
# 1. Aplicar namespace
kubectl apply -f k8s/namespace.yaml

# 2. Verificar namespace
kubectl get namespace magnix-app

# 3. Deployar MySQL
kubectl apply -f k8s/mysql.yaml

# 4. Verificar MySQL
kubectl get pods -n magnix-app

# 5. Deployar la aplicación
kubectl apply -f k8s/magnix-app.yaml

# 6. Verificar deployment
kubectl get all -n magnix-app

# 7. Ver logs
kubectl logs -f deployment/magnix-app -n magnix-app

# 8. Acceder a la aplicación
kubectl port-forward svc/magnix-app 8080:80 -n magnix-app
# Luego abrir: http://localhost:8080/magnix/actuator/health
\\\

## 🔍 Comandos útiles

\\\ash
# Ver todos los recursos
kubectl get all -n magnix-app

# Ver detalles de un pod
kubectl describe pod <pod-name> -n magnix-app

# Ver logs
kubectl logs <pod-name> -n magnix-app

# Conectarse a un pod
kubectl exec -it <pod-name> -n magnix-app -- /bin/bash

# Eliminar todo
kubectl delete namespace magnix-app
\\\

## 📊 Características

### Alta Disponibilidad
- 2 réplicas de la aplicación
- Health checks (liveness y readiness)
- RollingUpdate sin downtime

### Escalabilidad
- HorizontalPodAutoscaler configurado
- Escala de 2 a 10 réplicas automáticamente
- Basado en CPU (70%) y Memoria (80%)

### Recursos
- Requests definidos para scheduling óptimo
- Limits para evitar consumo excesivo

## ⚠️ Notas importantes

1. **Antes de deployar**: Asegúrate de que la imagen Docker esté en el registry
2. **Credenciales**: En producción, usa Kubernetes Secrets en lugar de valores hardcodeados
3. **Persistencia**: MySQL no tiene volumen persistente en esta versión básica
4. **LoadBalancer**: Funciona en cloud providers (GKE, EKS, AKS). En local usa NodePort o port-forward

## 🔐 Mejoras de seguridad (para producción)

Para producción, deberías:

1. Usar Secrets para credenciales:
\\\yaml
apiVersion: v1
kind: Secret
metadata:
  name: mysql-secrets
  namespace: magnix-app
type: Opaque
data:
  password: <base64-encoded-password>
\\\

2. Agregar Network Policies
3. Configurar RBAC
4. Usar PersistentVolumes para MySQL
5. Agregar Ingress con TLS

## 📚 Recursos adicionales

- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [kubectl Cheat Sheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)
