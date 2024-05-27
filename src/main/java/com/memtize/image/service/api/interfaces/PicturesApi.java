package com.memtize.image.service.api.interfaces;

import com.memtize.image.service.api.dto.ErrorForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "crypto-pictures", description = "Crypto pictures controller")
public interface PicturesApi {

    @Operation(
        summary = "Add new crypto's main picture",
        description = "Add new crypto's main picture",
        tags = "crypto-pictures",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(
                responseCode = "404",
                description = "Not found",
                content =
                @Content(
                    schema = @Schema(implementation = ErrorForm.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
        })
    ResponseEntity<Void> addMainPicture(
        @PathVariable String id, @RequestPart(name = "file") MultipartFile file) throws IOException;

    @Operation(
        summary = "Get crypto's profile main for specified id",
        description = "Get crypto's profile main for specified id",
        tags = "crypto-pictures",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful",
                content =
                @Content(
                    schema = @Schema(implementation = byte[].class),
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE)),
            @ApiResponse(
                responseCode = "404",
                description = "Not found",
                content =
                @Content(
                    schema = @Schema(implementation = ErrorForm.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
        })
    ResponseEntity<Resource> getMainPicture(@PathVariable String id, Boolean archive)
        throws IOException;

    @Operation(
        summary = "Delete crypto's main picture for specified id",
        description = "Delete crypto's main picture for specified id",
        tags = "crypto-pictures",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No content")})
    ResponseEntity<Void> deleteMainPicture(@PathVariable String id);
}
