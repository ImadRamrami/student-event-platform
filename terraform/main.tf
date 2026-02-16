terraform {
  required_providers {
    kubernetes = {
      source = "hashicorp/kubernetes"
      version = "2.23.0"
    }
  }
}

provider "kubernetes" {
  config_path = "~/.kube/config"
}

resource "kubernetes_deployment" "student_event_platform" {
  metadata {
    name = "student-event-platform"
    labels = {
      app = "student-event-platform"
    }
  }

  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "student-event-platform"
      }
    }
    template {
      metadata {
        labels = {
          app = "student-event-platform"
        }
      }
      spec {
        container {
          image = "student-event-platform:latest"
          name  = "student-event-platform"
          port {
            container_port = 8080
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "student_event_platform_service" {
  metadata {
    name = "student-event-platform-service"
  }
  spec {
    selector = {
      app = "student-event-platform"
    }
    port {
      port        = 80
      target_port = 8080
    }
    type = "NodePort"
  }
}
