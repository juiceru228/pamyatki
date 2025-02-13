FROM golang:1.22-alpine

RUN apk add --no-cache git

WORKDIR /app

COPY auth/go.mod auth/go.sum ./

RUN go mod download

COPY auth/. .

RUN go build -o main .

EXPOSE 8080

CMD ["/app/main"]