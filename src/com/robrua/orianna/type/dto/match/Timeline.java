package com.robrua.orianna.type.dto.match;

import java.util.List;

import com.robrua.orianna.type.dto.OriannaDto;

public class Timeline extends OriannaDto {
    private static final long serialVersionUID = 6097409442140309148L;
    private Long frameInterval;
    private List<Frame> frames;

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Timeline)) {
            return false;
        }
        final Timeline other = (Timeline)obj;
        if(frameInterval == null) {
            if(other.frameInterval != null) {
                return false;
            }
        }
        else if(!frameInterval.equals(other.frameInterval)) {
            return false;
        }
        if(frames == null) {
            if(other.frames != null) {
                return false;
            }
        }
        else if(!frames.equals(other.frames)) {
            return false;
        }
        return true;
    }

    /**
     * @return the frameInterval
     */
    public Long getFrameInterval() {
        return frameInterval;
    }

    /**
     * @return the frames
     */
    public List<Frame> getFrames() {
        return frames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (frameInterval == null ? 0 : frameInterval.hashCode());
        result = prime * result + (frames == null ? 0 : frames.hashCode());
        return result;
    }

    /**
     * @param frameInterval
     *            the frameInterval to set
     */
    public void setFrameInterval(final Long frameInterval) {
        this.frameInterval = frameInterval;
    }

    /**
     * @param frames
     *            the frames to set
     */
    public void setFrames(final List<Frame> frames) {
        this.frames = frames;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Timeline [frameInterval=" + frameInterval + ", frames=" + frames + "]";
    }
}
