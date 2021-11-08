package io.github.nimbo1999.utils;

import java.time.Instant;
import java.time.ZoneId;

public class InstantUtils {
    private static final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    public static Instant instantNow() {
        return Instant
            .now()
            .atZone(zoneId)
            .toInstant();
    }
}