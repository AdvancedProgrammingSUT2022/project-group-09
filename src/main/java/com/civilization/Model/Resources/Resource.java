package com.civilization.Model.Resources;

public class Resource {
    ResourceMainType myMainType;
    ResourceType myType;

    public Resource(ResourceMainType myMainType, ResourceType myType) {
        this.myMainType = myMainType;
        this.myType = myType;
    }

    public ResourceMainType getMyMainType() {
        return myMainType;
    }

    public void setMyMainType(ResourceMainType myMainType) {
        this.myMainType = myMainType;
    }

    public ResourceType getMyType() {
        return myType;
    }

    public void setMyType(ResourceType myType) {
        this.myType = myType;
    }
}
