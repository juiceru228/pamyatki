package main

import (
	"context"
	"fmt"
	"log"
	"net"

	pb "auth/gen/go/pamyatki.sso.v1"

	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

type authServer struct {
	pb.UnimplementedAuthServer
}

func (s *authServer) Register(ctx context.Context, req *pb.RegisterRequest) (*pb.RegisterResponse, error) {
	log.Printf("Register request: %v", req)
	return &pb.RegisterResponse{Success: true, Message: "User registered"}, nil
}

func (s *authServer) Login(ctx context.Context, req *pb.LoginRequest) (*pb.LoginResponse, error) {
	log.Printf("Login request: %v", req)
	return &pb.LoginResponse{Token: "mock-token", ExpiresIn: 3600, RefreshToken: "mock-refresh"}, nil
}

func main() {
	listener, err := net.Listen("tcp", ":8080")
	if err != nil {
		log.Fatalf("Failed to listen: %v", err)
	}

	server := grpc.NewServer()
	pb.RegisterAuthServer(server, &authServer{})
	reflection.Register(server)
	fmt.Println("Auth service running on port 8080")
	if err := server.Serve(listener); err != nil {
		log.Fatalf("Failed to serve: %v", err)
	}
}
