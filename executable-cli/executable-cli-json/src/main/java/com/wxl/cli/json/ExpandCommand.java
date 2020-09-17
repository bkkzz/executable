package com.wxl.cli.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/16
 * json 展开
 * 默认
 */
public class ExpandCommand extends JsonCommand {

    private static final Option EXPAND_OPT = Option.builder("e")
            .longOpt("expand")
            .desc("json展开，默认")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[json]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        String jsonStr;
        if (isCurrentCommand(context)) {
            jsonStr = getRequireOptionValue(context, 0);
        } else {
            jsonStr = getRequireArgValue(context, 0);
        }

        GsonBuilder gsonBuilder = getGsonBuilder(context);
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        JsonElement element = parseJson(jsonStr, context);

        String expandJson = gson.toJson(element);

        context.stdout().println(expandJson);
    }

    @Override
    public Option option() {
        return EXPAND_OPT;
    }
}
