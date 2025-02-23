package com.matteof_mattos.workshopMongo.exceptions;

import java.time.Instant;

public record StandardError(Instant timestamp, int status, String error, String path) {

}
