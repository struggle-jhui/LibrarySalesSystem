package com.epam.librarysalessystem.ScoreHandler;

import com.epam.librarysalessystem.entity.MemberType;

import java.util.HashMap;
import java.util.Map;

/**
 * ScoreHandlerFactory
 *
 * @Since 2022/3/18
 */
public class ScoreHandlerFactory {
    private static final Map<MemberType, ScoreHandler> SCOREH_ANDLER_MAP = new HashMap<>();

    public static ScoreHandler getScoreHandler(MemberType memberType) {
        return SCOREH_ANDLER_MAP.get(memberType);
    }

    public static void register(MemberType memberType, ScoreHandler scoreHandler) {
        SCOREH_ANDLER_MAP.put(memberType, scoreHandler);
    }
}
