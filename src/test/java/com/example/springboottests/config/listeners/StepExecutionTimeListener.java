package com.example.springboottests.config.listeners;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class StepExecutionTimeListener implements StepLifecycleListener {

    @Getter
    private static Map<String, List<Long>> stepToTimeMap = Maps.newHashMap();
    private static Long lastSum = 0L;
    @Getter
    private static List<Long> listOfSums = Lists.newArrayList(List.of(0L));
    @Getter
    private static List<Long> listOfElapseds = Lists.newArrayList(List.of(0L));

    public static Map<String, Double> getAverageStepDuration(){
        Map<String, Double> result = Maps.newHashMap();
        stepToTimeMap.forEach((k, v)->result.put(k, v.stream().mapToDouble(d->d).average().orElse(0.0)));
        return result;
    }

    public static Map<String, Long> getTotalElapsedPerStep(){
        Map<String, Long> result = Maps.newHashMap();
        stepToTimeMap.forEach((k, v)->result.put(k, v.stream().mapToLong(Long::longValue).sum()));
        return result;
    }

    @SneakyThrows
    @Override
    public void afterStepStop(StepResult result) {
        if (!result.getName().contains("getBy")) {
            Long elapsed = result.getStop() - result.getStart();
            stepToTimeMap.computeIfAbsent(result.getName(), k -> Lists.newArrayList());
            stepToTimeMap.get(result.getName()).add(elapsed);
            lastSum += elapsed;
            listOfSums.add(lastSum);
            listOfElapseds.add(elapsed);
        }
    }
}
