#!/bin/bash

# GitHubリポジトリ情報
USER="gpioblink"           # GitHubのユーザー名または組織名
REPO="karaoke-tools"     # リポジトリ名
BRANCH="main"              # 取得するブランチ名 (デフォルトは "main")
BBFILE="karaoke-remote_0.1.bb"

# GitHub APIを使って最新のコミットを取得
LATEST_COMMIT=$(curl -s "https://api.github.com/repos/$USER/$REPO/commits/$BRANCH" | grep '"sha"' | head -n 1 | sed -E 's/.*"sha": "([^"]+)".*/\1/')
echo "$LATEST_COMMIT"

# SRCREVフィールドを書き換える
if [ -n "$LATEST_COMMIT" ]; then
  # .bbファイルのSRCREVを最新のコミットハッシュに置き換える
  sed -i -E "s/(SRCREV = \").*\"/\1${LATEST_COMMIT}\"/" "$BBFILE"
  echo "Updated SRCREV to latest commit: $LATEST_COMMIT"
else
  echo "Failed to fetch the latest commit hash."
fi

