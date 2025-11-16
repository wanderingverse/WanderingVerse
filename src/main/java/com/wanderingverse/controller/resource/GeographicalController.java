package com.wanderingverse.controller.resource;

import com.wanderingverse.service.resource.GeographicalService;
import com.wanderingverse.util.AjaxResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WanderingVerse
 * @since 2025/11/15 21:32
 */
@Slf4j
@RestController
@RequestMapping("/graphical")
public class GeographicalController {
    @Resource
    private GeographicalService geographicalService;

    /**
     * 获取地市级信息
     */
    @GetMapping("/toponym_list")
    public AjaxResult getToponymList() {
        return null;
    }
}
