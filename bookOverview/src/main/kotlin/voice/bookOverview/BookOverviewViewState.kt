package voice.bookOverview

import voice.data.Book
import java.io.File

sealed interface BookOverviewViewState {

  val layoutIcon: Content.LayoutIcon?
  val playButtonState: PlayButtonState?

  object Loading : BookOverviewViewState {
    override val playButtonState: PlayButtonState? = null
    override val layoutIcon: Content.LayoutIcon? = null
  }

  data class Content(
    val books: Map<BookOverviewCategory, List<BookViewState>>,
    val layoutMode: LayoutMode,
    override val layoutIcon: LayoutIcon?,
    override val playButtonState: PlayButtonState?,
  ) : BookOverviewViewState {

    data class BookViewState(
      val name: String,
      val author: String?,
      val cover: File?,
      val progress: Float,
      val id: Book.Id,
      val remainingTime: String,
    )

    enum class LayoutMode {
      List, Grid
    }

    enum class LayoutIcon {
      List, Grid
    }
  }

  enum class PlayButtonState {
    Playing, Paused
  }
}