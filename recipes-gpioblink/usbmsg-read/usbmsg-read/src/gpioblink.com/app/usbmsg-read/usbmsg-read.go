package main

import (
    "bufio"
    "fmt"
    "os"
    "regexp"
    "time"
)

// kmsg監視用の関数
func watchKmsg(messages chan<- string) {
    // 正規表現を用意して、パターンを指定
    re := regexp.MustCompile(`lun0: file read (\d+) @ (\d+)`)

    for {
        // /dev/kmsgを開く
        file, err := os.Open("/dev/kmsg")
        if err != nil {
            fmt.Printf("Failed to open /dev/kmsg: %v\n", err)
            time.Sleep(time.Second) // エラーが発生した場合は1秒待機して再試行
            continue
        }

        // kmsgを監視して、各行を読み込む
        scanner := bufio.NewScanner(file)
        for scanner.Scan() {
            line := scanner.Text()
            // 正規表現にマッチするかチェック
            matches := re.FindStringSubmatch(line)
            if len(matches) == 3 {
                length := matches[1]
                address := matches[2]
                // 「READ <Address> <Length>」の形式のメッセージをチャネルに送信
                messages <- fmt.Sprintf("READ %s %s", address, length)
            }
        }

        if err := scanner.Err(); err != nil {
            fmt.Printf("Error reading from /dev/kmsg: %v\n", err)
        }

        file.Close() // ファイルを閉じる

        // 無限ループで再度監視を続ける
        time.Sleep(time.Second) // 1秒待機して再度オープン
    }
}

