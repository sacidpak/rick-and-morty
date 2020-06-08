package com.rickandmorty.response;

import com.rickandmorty.data.report.LogDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sacidpak
 */
@Getter
@Setter
public class ReportResponse {
    private List<ReportBaseInfo> reportInfo = new ArrayList<>();
    private List<LogDTO> report = new ArrayList<>();
}
