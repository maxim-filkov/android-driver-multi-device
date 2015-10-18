package com.multidevice.test.framework;

import java.util.Arrays;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * This is a base class for all JBehave stories.
 *
 * @author mfilkov
 */
public abstract class AbstractSpringJBehaveStory extends JUnitStory {

  private static final int STORY_TIMEOUT_SECS = 200000;

  @Autowired
  private ApplicationContext applicationContext;

  /**
   * Creates an instance of AbstractSpringJBehaveStory.
   */
  public AbstractSpringJBehaveStory() {
    final Embedder embedder = new Embedder();
    embedder.useEmbedderControls(embedderControls());
    embedder.useMetaFilters(Arrays.asList("-skip"));
    useEmbedder(embedder);
  }

  @Override
  public Configuration configuration() {
    return new MostUsefulConfiguration()
        .useStoryPathResolver(storyPathResolver())
        .useStoryLoader(storyLoader())
        .useStoryReporterBuilder(storyReporterBuilder())
        .useParameterControls(parameterControls());
  }

  @Override
  public InjectableStepsFactory stepsFactory() {
    return new SpringStepsFactory(configuration(), applicationContext);
  }

  private EmbedderControls embedderControls() {
    return new EmbedderControls().doIgnoreFailureInView(true).useStoryTimeoutInSecs(STORY_TIMEOUT_SECS);
  }

  private ParameterControls parameterControls() {
    return new ParameterControls().useDelimiterNamedParameters(true);
  }

  private StoryPathResolver storyPathResolver() {
    return new UnderscoredCamelCaseResolver();
  }

  private StoryLoader storyLoader() {
    return new LoadFromClasspath();
  }

  private StoryReporterBuilder storyReporterBuilder() {
    return new StoryReporterBuilder()
        .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
        .withPathResolver(new ResolveToPackagedName())
        .withFailureTrace(true)
        .withDefaultFormats()
        .withFormats(Format.IDE_CONSOLE, Format.TXT, Format.HTML);
  }

}
