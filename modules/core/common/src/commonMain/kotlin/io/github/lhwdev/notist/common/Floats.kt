package io.github.lhwdev.notist.common


private const val SimilarityThreshold = 1e-5f


infix fun Float.similarTo(other: Float): Boolean {
	val diff = this - other
	return diff < SimilarityThreshold && diff > -SimilarityThreshold
}
