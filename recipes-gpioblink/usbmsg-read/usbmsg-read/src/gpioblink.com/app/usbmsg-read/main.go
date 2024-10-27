package main

import "fmt"

func main() {
    // メッセージ用のチャネルを作成
    messages := make(chan string)

    // 別のゴルーチンでkmsg監視を開始
    go watchKmsg(messages)

    // メインスレッドでチャネルからのメッセージを表示
    for msg := range messages {
        fmt.Println(msg)
    }
}

