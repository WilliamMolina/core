package org.jboss.forge.addon.projects;

import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.context.UINavigationContext;
import org.jboss.forge.addon.ui.result.NavigationResult;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.wizard.UIWizardStep;

/**
 * Provides additional project configuration for use during new project creation.
 *
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
public interface ProjectType
{
   /**
    * Return the human-readable name for this {@link ProjectType}. This should be relatively unique.
    */
   String getType();

   /**
    * Return the {@link UIWizardStep} {@link Class} that begins the {@link Project} configuration of this
    * {@link ProjectType}.
    * 
    * 
    * The default implementation returns <code>null</code>. Implementations are encouraged to override the
    * {@link #getSetupFlow()} method instead.
    * 
    */
   default Class<? extends UIWizardStep> getSetupFlow()
   {
      return null;
   }

   /**
    * Return the {@link NavigationResult} that begins {@link Project} configuration of this {@link ProjectType}.
    * 
    * The default implementation calls <code>Results.navigateTo(getSetupFlow())</code>
    * 
    * @param context the current {@link UINavigationContext}
    * 
    * @return a {@link NavigationResult} with the next steps to follow
    */
   default NavigationResult next(UINavigationContext context)
   {
      return Results.navigateTo(getSetupFlow());
   }

   /**
    * Return all {@link ProjectFacet} {@link Class} types required by a {@link Project} of this {@link ProjectType}.
    */
   Iterable<Class<? extends ProjectFacet>> getRequiredFacets();

   /**
    * Defines the priority of this {@link ProjectType}. Lower values receive a higher priority.
    */
   int priority();

   /**
    * Returns if this {@link ProjectType} is enabled in the current {@link UIContext}
    */
   default boolean isEnabled(UIContext context)
   {
      return true;
   }
}
