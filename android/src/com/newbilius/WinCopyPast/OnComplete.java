package com.newbilius.WinCopyPast;

public interface OnComplete {
    void Complete();
    void Process(String text);
    void Error(String text);
}
