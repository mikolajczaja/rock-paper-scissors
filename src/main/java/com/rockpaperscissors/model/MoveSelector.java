package com.rockpaperscissors.model;

/**
 * Functional interface used for facilitating the definition of {@link Move} selection strategy used by a {@link Player}
 */
public interface MoveSelector {
	Move execute();
}