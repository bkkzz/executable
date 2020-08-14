package com.wxl.cli;

/**
 * Create by wuxingle on 2020/08/07
 * 默认错误处理
 */
public class DefaultCommandErrorHandler implements CommandErrorHandler {

    @Override
    public void handleError(CommandContext context, Throwable error) {
        context.stderr().println(error.getMessage());
    }
}
