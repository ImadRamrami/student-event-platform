provider "aws" {
  region = "eu-west-3" # Paris
}

# 1. ECR Repository (Where our Docker image lives)
resource "aws_ecr_repository" "app_repo" {
  name                 = "student-event-platform"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true # Security First!
  }
}

# 2. ECS Cluster (Where our app runs)
resource "aws_ecs_cluster" "main" {
  name = "student-event-platform-cluster"
  
  setting {
    name  = "containerInsights"
    value = "enabled" # Monitoring enabled by default
  }
}

# 3. Task Definition (BluePrint for the app)
resource "aws_ecs_task_definition" "app" {
  family                   = "student-event-platform"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = 256
  memory                   = 512

  container_definitions = jsonencode([
    {
      name      = "student-event-platform"
      image     = "${aws_ecr_repository.app_repo.repository_url}:latest"
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
      # FinOps: Environment variables for cost allocation
      environment = [
        {
          name  = "COST_CENTER"
          value = "STUDENT_PROJECT_01"
        }
      ]
    }
  ])
}
