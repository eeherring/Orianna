package com.robrua.orianna.type.dto.match;

import com.robrua.orianna.type.dto.OriannaDto;

public class CombinedParticipant extends OriannaDto {
    private static final long serialVersionUID = 8480309852405991908L;
    private ParticipantIdentity identity;
    private Participant participant;

    /**
     *
     */
    public CombinedParticipant() {
        identity = null;
        participant = null;
    }

    /**
     * @param participant
     *            the participant
     * @param identity
     *            the identity
     */
    public CombinedParticipant(final Participant participant, final ParticipantIdentity identity) {
        this.identity = identity;
        this.participant = participant;
    }

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
        if(!(obj instanceof CombinedParticipant)) {
            return false;
        }
        final CombinedParticipant other = (CombinedParticipant)obj;
        if(identity == null) {
            if(other.identity != null) {
                return false;
            }
        }
        else if(!identity.equals(other.identity)) {
            return false;
        }
        if(participant == null) {
            if(other.participant != null) {
                return false;
            }
        }
        else if(!participant.equals(other.participant)) {
            return false;
        }
        return true;
    }

    /**
     * @return the identity
     */
    public ParticipantIdentity getIdentity() {
        return identity;
    }

    /**
     * @return the participant
     */
    public Participant getParticipant() {
        return participant;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (identity == null ? 0 : identity.hashCode());
        result = prime * result + (participant == null ? 0 : participant.hashCode());
        return result;
    }

    /**
     * @param identity
     *            the identity to set
     */
    public void setIdentity(final ParticipantIdentity identity) {
        this.identity = identity;
    }

    /**
     * @param participant
     *            the participant to set
     */
    public void setParticipant(final Participant participant) {
        this.participant = participant;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CombinedParticipant [participant=" + participant + ", identity=" + identity + "]";
    }
}
