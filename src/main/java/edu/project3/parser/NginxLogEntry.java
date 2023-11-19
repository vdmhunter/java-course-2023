package edu.project3.parser;

import java.net.InetAddress;
import java.net.URI;
import java.time.OffsetDateTime;

/**
 * A record representing a single entry in an NGINX log file.
 *
 * @param remoteAddress The IP address of the client that made the request.
 * @param remoteUser    The username of the client, as identified by HTTP authentication.
 * @param timeLocal     The time at which the request was received, in the local time zone.
 * @param request       The request line from the client. Formatted as a string like "GET / HTTP/1.1".
 * @param status        The HTTP status code returned to the client.
 * @param bodyBytesSent The size (in bytes) of the object returned to the client, not including the response headers.
 * @param httpReferer   The URL of the page that linked to the requested file.
 * @param httpUserAgent The User-Agent HTTP header. Contains info about the client's browser and operating system.
 */
public record NginxLogEntry(
    InetAddress remoteAddress,
    String remoteUser,
    OffsetDateTime timeLocal,
    String request,
    int status,
    long bodyBytesSent,
    URI httpReferer,
    String httpUserAgent
) {
}

