package com.simu.core.dao;

import com.simu.core.event.Observer;

public record AddSuscriberToComponents(
        Class<?> componentClass,
        Observer subscriber
) {
}
